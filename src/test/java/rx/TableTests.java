package rx;

import api.dto.rx.publisher.Publisher;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.dashbord.DashboardPage;
import pages.publisher.PublishersPage;
import widgets.common.table.Table;
import widgets.common.table.TableOptions;
import widgets.common.table.TableOptionsElements;
import widgets.common.table.TablePagination;
import zutils.FakerUtils;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class TableTests extends BaseTest{

    private Publisher publisher;
    private PublishersPage publishersPage;
    private Table table;
    private TablePagination tablePagination;
    private TableOptions tableOptions;

    public TableTests(){
        publishersPage = new PublishersPage();
        table = new Table();
        tableOptions = new TableOptions();
    }

    @BeforeClass
    public void createNewPublisher(){
        //Creating publisher to edit Using API
        publisher = PublisherPrecondition.publisher()
                .createNewPublisher()
                .build()
                .getPublisherResponse();
    }

    @Test
    public void checkColumns(){
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .and()
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .unSelectCheckBox(tableOptions.getMenuItemState("Publisher"))
                .testEnd();

    }
}
