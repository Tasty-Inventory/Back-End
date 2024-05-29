//package net.skhu.tastyinventory_be.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import net.skhu.tastyinventory_be.common.dto.BaseResponse;
//import net.skhu.tastyinventory_be.exception.ErrorCode;
//import org.springframework.http.MediaType;
//
//import java.io.IOException;
//
//@Slf4j
//public class ServletErrorResponse {
//    public static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        response.setStatus(errorCode.getHttpStatusCode());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//        try {
//            response.getWriter().write(
//                    objectMapper.writeValueAsString(BaseResponse.error(errorCode, errorCode.getMessage()))
//            );
//        } catch (IOException e) {
//            log.error("입출력 에러: {}", e.getMessage(), e);
//        }
//    }
//}
