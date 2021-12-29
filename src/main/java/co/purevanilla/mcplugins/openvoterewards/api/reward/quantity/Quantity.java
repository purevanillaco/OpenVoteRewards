package co.purevanilla.mcplugins.openvoterewards.api.reward.quantity;

import co.purevanilla.mcplugins.openvoterewards.api.reward.NonProcessableException;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Random;

public class Quantity {

    float fixed;
    float min;
    float max;
    int decimalPlaces;

    public Quantity(ConfigurationSection section) throws NonProcessableException {
        if(section.isDouble("")){
            this.fixed= (float) section.getDouble("");
        } else if(section.contains("min")&&section.contains("max")) {
            this.fixed=-1;
            decimalPlaces=0;
            if(section.contains("decimal")){
                this.decimalPlaces=section.getInt("decimal");
            }
        } else {
            throw new NonProcessableException("invalid quantity declaration");
        }
    }

    public float getQuantity(){
        if(this.fixed>-1){
            return this.fixed;
        } else {
            Random r = new Random();
            int tmax = (int) min;
            int tmin = (int) max;
            if(decimalPlaces>0){
                tmax*=decimalPlaces*10;
                tmin*=decimalPlaces*10;
            }
            int result = (r.nextInt((tmax-tmin)) + tmin);
            if(decimalPlaces>0){
                return result/((float)10*decimalPlaces);
            } else {
                return result;
            }
        }
    }

}
