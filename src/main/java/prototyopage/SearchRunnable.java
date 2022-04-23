package prototyopage;

import DB.SejourDB.Sejour;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;

public class SearchRunnable implements Runnable {
    private List<Sejour> list;
    private RechercheController controller;

    public SearchRunnable(List<Sejour> list, RechercheController controller) {
        this.list = list;
        this.controller=controller;
    }

    public void run() {
        for (int i = 0; i < list.size(); i++) {
            Boolean find = false;
            for (int j = 0; j < controller.getSejourList().size(); j++) {
                if (controller.getSejourList().get(j).getSejourId() == list.get(i).getSejourId()) {
                    find = true;
                }
            }
            if (find == false) {
                controller.addToSejourList(list.get(i));
                Text sejour = new Text();
                String sejourText="";
                sejourText+=list.get(i).getName()+"\nOù? ";
                sejourText+=list.get(i).getLocation()+"\nQuand? De ";
                String date=list.get(i).getDateBegin().toInstant().toString();
                String[] separated = date.split("T");
                sejourText+=separated[0];
                sejourText+=" jusqu'à ";
                date=list.get(i).getDateEnd().toInstant().toString();
                separated = date.split("T");
                sejourText+=separated[0];
                sejour.setText(sejourText);
                Button button=new Button("",sejour);
                button.setMaxWidth(10000000);
                button.setAlignment(Pos.BASELINE_LEFT);
                button.setId(String.valueOf(list.get(i).getSejourId()));
                button.setOnAction((event) -> {    // lambda expression
                    controller.openSejour(Integer.parseInt(button.getId()));
                    System.out.println("open sejour: " + button.getId());
                });
                controller.addToBoxSejour(button);
            }
        }
    }


}
