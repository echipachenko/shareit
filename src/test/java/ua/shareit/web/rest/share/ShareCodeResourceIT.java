package ua.shareit.web.rest.share;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import ua.shareit.ShareitApp;
import ua.shareit.domain.ShareCode;
import ua.shareit.repository.ShareCodeRepository;
import ua.shareit.web.rest.TestUtil;
import ua.shareit.web.rest.vm.ShareCodeVM;

/**
 * Integration tests for the {@link ShareCodeResource} REST controller.
 */
@SpringBootTest(classes = ShareitApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShareCodeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    @Autowired
    private ShareCodeRepository shareCodeRepository;

    @Autowired
    private MockMvc restShareCodeMockMvc;

    @Test
    @Transactional
    public void createShareCode() throws Exception {
        int databaseSizeBeforeCreate = shareCodeRepository.findAll().size();
        ShareCodeVM vm = new ShareCodeVM();
        vm.setCode(DEFAULT_CODE);

        // Create the ShareCode
        restShareCodeMockMvc.perform(post("/api/share/code")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isCreated());

        // Validate the ShareCode in the database
        List<ShareCode> shareCodeList = shareCodeRepository.findAll();
        assertThat(shareCodeList).hasSize(databaseSizeBeforeCreate + 1);
        ShareCode testShareCode = shareCodeList.get(shareCodeList.size() - 1);
        assertThat(testShareCode.getUid()).isNotNull();
        assertThat(testShareCode.getExpired()).isNotNull();
        assertThat(testShareCode.getCreated()).isNotNull();
        assertThat(testShareCode.getCode()).isEqualTo(DEFAULT_CODE);
    }

}
