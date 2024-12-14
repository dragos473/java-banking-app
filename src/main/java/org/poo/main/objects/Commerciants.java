package org.poo.main.objects;

import lombok.Getter;
import lombok.Setter;
import org.poo.main.objects.Actions.Report;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Commerciants {
    List<String> names;
    List<Double> moneySpent;


    public Commerciants() {
        moneySpent = new ArrayList<>();
        names = new ArrayList<>();
    }

    public void addCommerciants(String name, double moneySpent) {
        if (names.isEmpty()) {
            names.add(name);
            this.moneySpent.add(moneySpent);
            return;
        }

        if (names.contains(name)) {
            int index = names.indexOf(name);
            this.moneySpent.set(index, this.moneySpent.get(index) + moneySpent);
            return;
        }

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).compareTo(name) > 0) {
                names.add(i, name);
                this.moneySpent.add(i, moneySpent);
                return;
            }
        }
        names.addLast(name);
        this.moneySpent.addLast(moneySpent);
    }
}
