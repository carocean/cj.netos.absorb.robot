package cj.netos.absorb.robot.bo;

import java.math.BigDecimal;
import java.util.Map;

public class AbsorberTemplate {
    AbsorberRule global;
    Map<String, AbsorberRule> categories;

    public AbsorberRule getGlobal() {
        return global;
    }

    public void setGlobal(AbsorberRule global) {
        this.global = global;
    }

    public Map<String, AbsorberRule> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, AbsorberRule> categories) {
        this.categories = categories;
    }

    public BigDecimal getCategoryWeight(String category) {
        AbsorberRule rule = categories.get(category);
        if (rule == null) {
            return global.weight;
        }
        return rule.weight == null ? global.weight : rule.weight;
    }

    public long getCategoryExitExpire(String category) {
        AbsorberRule rule = categories.get(category);
        if (rule == null) {
            return global.exitExpire;
        }
        return rule.exitExpire;
    }

    public long getCategoryExitAmount(String category) {
        AbsorberRule rule = categories.get(category);
        if (rule == null) {
            return global.exitAmount;
        }
        return rule.exitAmount;
    }

    public long getCategoryExitTimes(String category) {
        AbsorberRule rule = categories.get(category);
        if (rule == null) {
            return global.exitTimes;
        }
        return rule.exitTimes;
    }

    public long getCategoryMaxRecipients(String category) {
        AbsorberRule rule = categories.get(category);
        if (rule == null) {
            return global.maxRecipients;
        }
        return rule.maxRecipients;
    }

    public BigDecimal getCategoryEncourage(String category, String key) {
        AbsorberRule rule = categories.get(category);
        if (rule == null) {
            return global.encourage.get(key);
        }
        return rule.encourage.get(key);
    }
}