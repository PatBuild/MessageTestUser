import frameWork.Constants;
import frameWork.MessageActions;
import frameWork.objects.Message;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created PMG 05-2021
 *  Misc functions to clean environment variables  PSCamp message API
 */
public class CleanUp extends BaseTest {

    String testId =Constants.testID+"mft_";

    @Test
    public void _clear_messages() {
       MessageActions.deleteMessage(MessageActions.getMessages("PSFC_mft_un1_3","PSFC_mft_un2_3"));
    }



}
