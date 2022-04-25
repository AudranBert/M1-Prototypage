package prototyopage.Controllers;

import DB.SejourDB.Sejour;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import prototyopage.Context;
import prototyopage.MainApp;

import java.util.List;

public class SearchRunnable implements Runnable {
    private List<Sejour> list;
    private RechercheController controller;
    private MainApp mainApp;

    public SearchRunnable(List<Sejour> list, RechercheController controller, MainApp mainApp) {
        this.list = list;
        this.controller=controller;
        this.mainApp = mainApp;
    }

    public void run() {
        for (int i = 0; i < list.size(); i++) {
            Boolean find = false;

            if (controller.getListDisplayedSejour().contains(list.get(i))){
                find = true;
            }
//            for (int j = 0; j < controller.getSejourList().size(); j++) {
//                if (j < controller.getSejourList().size() && i < list.size() && controller.getSejourList().get(j).getSejourId() == list.get(i).getSejourId()) {
//
//                }
//            }
            if (find == false) {
                int index=i;
                controller.addToListDisplayedSejour(list.get(index));
                Text sejour = new Text();
                String sejourText="";
                sejourText+=list.get(index).getName()+"\nOù? ";
                sejourText+=list.get(index).getLocation()+"\n";
                sejourText+="Quand? De ";
                sejourText+=list.get(index).getStrDateBegin();
                sejourText+=" jusqu'à ";
                sejourText+=list.get(index).getStrDateEnd();
                sejour.setText(sejourText);
                Button button=new Button("",sejour);
                button.setMaxWidth(10000000);
                button.setAlignment(Pos.BASELINE_LEFT);
                button.setId(String.valueOf(list.get(index).getSejourId()));
                button.setOnAction((event) -> {    // lambda expression
                    Context.setSejourById(Integer.parseInt(button.getId()));
                    controller.openSejour();
                    System.out.println("open sejour: " + button.getId());
                });
                controller.addToBoxSejour(button);
            }
        }
    }


}
