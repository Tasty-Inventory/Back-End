package net.skhu.tastyinventory_be.domain.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InventoryRepositoryTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    public void 재고저장_불러오기(){
        // Inventory 객체 생성
        Inventory inventory = Inventory.builder()
                .name("새로운 재료")
                .unit("개")
                .imageUrl("http://example.com/image.png")
                .inventoryVolumes(new HashSet<>())
                .build();

        // Inventory 객체 저장
        inventoryRepository.save(inventory);

        // 모든 Inventory 객체 조회
        List<Inventory> result = inventoryRepository.findAll();

        // 검증
        assertThat(result).isNotEmpty(); // 결과가 비어있지 않아야 함
        assertThat(result.get(0).getName()).isEqualTo("새로운 재료"); // 저장된 Inventory의 이름 검증
    }
}

