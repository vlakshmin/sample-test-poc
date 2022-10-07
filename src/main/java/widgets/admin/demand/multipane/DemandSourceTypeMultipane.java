package widgets.admin.demand.multipane;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.multipane.Multipane;
import widgets.protections.protectiontypemultipane.ProtectionTypeMultipaneElements;
import widgets.protections.protectiontypemultipane.ProtectionTypeNameImpl;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.protections.protectiontypemultipane.ProtectionTypeMultipaneElements.*;



@Getter
public class DemandSourceTypeMultipane extends Multipane {

    public DemandSourceTypeMultipane(DemandSourceTypeNameImpl demandSourceTypeName){
        super(demandSourceTypeName);
    }
}