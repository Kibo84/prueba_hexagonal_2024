package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.ean.process.EanProcess;
import org.enriqueboronat.pruebahexagonal.config.security.jwt.JwtAuthenticationFilter;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.EanCodigoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.mapper.EanDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EanApiController.class)
@ActiveProfiles("test")
@WithMockUser
public class EanApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EanDtoMapper eanDtoMapper;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private EanProcess eanProcess;

    @Test
    public void getEanInfoShouldReturnOkWhenEanExists() throws Exception {
        when(eanProcess.searchEanInfo(any())).thenReturn(Either.right(Ean.builder()
                .eanCodigo(EanCodigoVO.of("8437008000101"))
                .producto(ProductoVO.builder().nombre("Producto 1").build())
                .proveedor(ProveedorVO.builder().nombre("Proveedor 1").build())
                .destino(DestinoVO.builder().descripcion("Destino 1").build())
                .build()));

        mockMvc.perform(get("/ean/{ean}", "8437008000101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}