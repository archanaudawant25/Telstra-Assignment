package mvx.component.com.myapplication;

/*
 * CountryDetailsActivity unit test cases.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import mvx.component.com.myapplication.ui.country.activity.CountryDetailsActivity;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class CountryDetailsActivityTest {

    private CountryDetailsActivity countryDetailsActivity;
    @Before
    public void setUpActivity(){
         countryDetailsActivity = Robolectric.
                setupActivity(CountryDetailsActivity.class);
    }


    @Test
    public void shouldNotBeNull(){
        assertNotNull(countryDetailsActivity);
    }


    @Test
    public void checkAppTitle(){
        String screenTitle = countryDetailsActivity.getString(R.string.screen_title_about_canada);
        assertThat("About Canada", equalTo(screenTitle));
    }


}
