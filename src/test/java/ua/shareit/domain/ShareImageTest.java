package ua.shareit.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ua.shareit.web.rest.TestUtil;

public class ShareImageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShareImage.class);
        ShareImage shareImage1 = new ShareImage();
        shareImage1.setId(1L);
        ShareImage shareImage2 = new ShareImage();
        shareImage2.setId(shareImage1.getId());
        assertThat(shareImage1).isEqualTo(shareImage2);
        shareImage2.setId(2L);
        assertThat(shareImage1).isNotEqualTo(shareImage2);
        shareImage1.setId(null);
        assertThat(shareImage1).isNotEqualTo(shareImage2);
    }
}
