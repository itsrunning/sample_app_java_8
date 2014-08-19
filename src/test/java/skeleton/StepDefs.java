package skeleton;
import com.snapci.microblog.MicroBlogConfiguration;
import cucumber.api.java.en.Given;

public class StepDefs {


    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(int arg1) throws Throwable {
    }

    private static DropwizardTestSupport<MicroBlogConfiguration> service;


}
