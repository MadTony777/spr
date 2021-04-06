package spr;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class UnitTests {
    public static String generatedString = RandomStringUtils.random(9, false, true);
    String arg = System.getProperty("arg", "test");
    public String environment = arg;
    Logger log = LoggerFactory.getLogger(UnitTests.class);

    @BeforeEach
    public void executedBeforeEach(TestInfo testInfo) {
        log.info("Starting test: "+ testInfo.getDisplayName());
    }

    @AfterEach
    public void executedAfterEach() {
        log.info("End test\n");
    }
    @Test
    public void rtdm_lan_wa_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.wa", "ESB_LAN");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }

    @Test
    public void rtdm_lan_b2b_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.b2b", "ESB_LAN");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }

    @Test
    public void rtdm_lan_shop_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.shop", "ESB_LAN");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }

    @Test
    public void rtdm_dmz_wa_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.wa", "ESB_DMZ");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }

    @Test
    public void rtdm_dmz_b2b_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.b2b", "ESB_DMZ");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }

//    @Test
//    public void rtdm_dmz_shop_COMPLETED() throws Exception {
//        String CID = generatedString;
//        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.shop", "ESB_DMZ");
//        assertThat(result, containsString( "Message received with CID:"+CID));
//    }



    @Test
    public void rtdm_lan_selection_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.selection", "ESB_LAN");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }

    @Test
    public void rtdm_dmz_selection_COMPLETED() throws Exception {
        String CID = generatedString;
        String result = Variability.reporter(environment, CID, "rtdm.sys.out.queue.selection", "ESB_DMZ");
        assertThat(result, containsString( "Message received with CID:"+CID));
    }
}
