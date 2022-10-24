package kitchenpos.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import kitchenpos.ControllerTest;
import kitchenpos.application.MenuGroupService;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MenuGroupRestController.class)
class MenuGroupRestControllerTest extends ControllerTest {

    @MockBean
    private MenuGroupService menuGroupService;

    @Test
    void 메뉴_그룹을_생성할_수_있다() throws Exception {
        // given
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(1L);
        menuGroup.setName("menu-group");

        given(menuGroupService.create(any(MenuGroup.class))).willReturn(menuGroup);

        // when
        ResultActions response = mockMvc.perform(post("/api/menu-groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new MenuGroup())));

        // then
        response
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(menuGroup)));
    }

    @Test
    void 메뉴_그룹_목록을_조회할_수_있다() throws Exception {
        // given
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(1L);
        menuGroup.setName("menu-group");
        when(menuGroupService.list()).thenReturn(Arrays.asList(menuGroup));

        // when
        ResultActions response = mockMvc.perform(get("/api/menu-groups"));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Arrays.asList(menuGroup))));
    }


}