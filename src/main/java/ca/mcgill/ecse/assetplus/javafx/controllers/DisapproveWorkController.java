package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusTicketingController;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.successful;
import java.sql.Date;
import java.time.LocalDate;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.Calendar;



public class DisapproveWorkController {

    private int ticketID;
    //private Date disapprovalDate;
    //private String reason;


    @FXML
    private TextField disapproveWorkComments;

    @FXML
    private Button cancelDisapproveWork;

    @FXML
    private Button updateDisapproveWork;

    @FXML
    private AnchorPane disapproveWorkAnchorPane;

    @FXML
    void cancelDisapproveWorkClicked(ActionEvent event) { // approveSelectedTicket
        disapproveWorkComments.setText("");
        sceneSwitch(disapproveWorkAnchorPane, "../pages/TicketsPage.fxml");
    }

    @FXML
    void updateDisapproveWorkClicked(ActionEvent event) { // this is a little sketchy atm
        String reason = disapproveWorkComments.getText();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date today = new Date(currentDate.getTime());
        if (successful(AssetPlusTicketingController.disapproveWorkOnMaintenanceTicket(ticketID, today, reason))){ 
            disapproveWorkComments.setText(""); 
            sceneSwitch(disapproveWorkAnchorPane, "../pages/TicketsPage.fxml");
        }
    }

    public void getSelectedTicketID(int id) {
        ticketID = id;
    }


}
