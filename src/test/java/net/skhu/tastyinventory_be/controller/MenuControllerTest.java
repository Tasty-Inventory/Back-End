package net.skhu.tastyinventory_be.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuSaveRequestDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuUpdateRequestDto;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;
import net.skhu.tastyinventory_be.service.MenuService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static net.skhu.tastyinventory_be.fixture.MenuFixture.MENU_1;
import static net.skhu.tastyinventory_be.fixture.MenuFixture.MENU_2;
import static net.skhu.tastyinventory_be.fixture.MenuFixture.MENU_3;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // Junit5 내에서 스프링 부트 테스트를 사용할 수 있게 해준다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 스프링 빈을 사용할 수 있게 환경을 로드하고 랜덤 포트에서 스프링을 실행한다.
@AutoConfigureMockMvc // @Controller, @Service, @Repository Bean을 모두 로드하여 준다.
public class MenuControllerTest {

    @LocalServerPort // 테스트용 랜덤 포트를 주입한다.
    private int port;

    @Autowired // 웹 관련 빈들을 관리하는 빈을 주입
    private WebApplicationContext context;

    @Autowired // 웹 API를 테스트할 때 사용. 가짜 요청을 생성할 수 있다.
    private MockMvc mockMvc;

    @BeforeEach // 한글 깨짐으로 인해 WebApplicationContext의 설정 변경을 통해 UTF-8로 강제 인코딩
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))  // 응답 인코딩을 UTF-8로 강제합니다.
                .build();
    }

    @Autowired
    ObjectMapper objectMapper; // 데이터 직렬화를 위한 객체

    @Autowired
    MenuService menuService; // StudentService 주입

    @Autowired
    MenuRepository menuRepository; // StudentRepository 주입

    @AfterEach // 각 테스트 종료 이후에 데이터베이스를 초기화
    public void afterEach() {
        menuRepository.deleteAll();
    }

    @Test
    @DisplayName("메뉴를 정상 저장한다")
    public void saveMenu() throws Exception {
        // given
        final MenuSaveRequestDto requestDto = MenuSaveRequestDto.builder()
                .name(MENU_1.getName())
                .price(MENU_1.getPrice())
                .build();

        String url = "http://localhost:"+ port + "/menus";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Menu> all = menuRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(MENU_1.getName());
        assertThat(all.get(0).getPrice()).isEqualTo(MENU_1.getPrice());
    }

    @Test
    @DisplayName("메뉴를 정상 조회한다")
    public void getMenu() throws Exception {
        // given
        menuRepository.save(MENU_2);

        String url = "http://localhost:" + port + "/menus/" + MENU_2.getId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final MenuResponseDto menuResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(menuResponse.getName()).isEqualTo(MENU_2.getName());
        assertThat(menuResponse.getPrice()).isEqualTo(MENU_2.getPrice());
    }

    @Test
    @DisplayName("메뉴 전체를 정상 조회한다")
    public void getAllMenu() throws Exception {
        // given
        menuRepository.save(MENU_1);
        menuRepository.save(MENU_2);
        menuRepository.save(MENU_3);

        String url = "http://localhost:" + port + "/menus";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final List<MenuResponseDto> actualResponses = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        List<MenuResponseDto> expectedResponses = menuService.findAll();
        assertThat(actualResponses).usingRecursiveComparison().isEqualTo(expectedResponses);
    }

    @Test
    @DisplayName("메뉴를 정상 수정한다")
    public void updateMenu() throws Exception {
        // given
        final Menu savedMenu = menuRepository.save(MENU_1);

        Long updateId = savedMenu.getId();
        String newName = "토마토파스타";
        Long newPrice = 40000L;

        MenuUpdateRequestDto requestDto = MenuUpdateRequestDto.builder()
                .name(newName)
                .price(newPrice)
                .build();

        String url = "http://localhost:" + port + "/menus/" + updateId;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Menu> all = menuRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(newName);
        assertThat(all.get(0).getPrice()).isEqualTo(newPrice);
    }

    @Test
    @DisplayName("메뉴를 정상 삭제한다")
    public void deleteMenu() throws Exception {
        // given
        final Menu savedMenu = menuRepository.save(MENU_1);

        String url = "http://localhost:" + port + "/menus/" + savedMenu.getId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Menu> all = menuRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
