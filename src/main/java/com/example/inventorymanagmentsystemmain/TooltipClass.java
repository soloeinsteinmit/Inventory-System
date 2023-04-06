package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 * Class for showing tooltips
 * */
public class TooltipClass {
    public static double tooltipDuration = 0.0;
    public static void tooltipMessage(String message, Tab tab){
        final Tooltip tooltip = new Tooltip();
        tooltip.setText(message);
        tooltip.setShowDelay(Duration.seconds(tooltipDuration));
        tab.setTooltip(tooltip);
    }

    public static void tooltipMessage(String message, MFXButton button){
        final Tooltip tooltip = new Tooltip();
        tooltip.setText(message);
        tooltip.setShowDelay(Duration.seconds(tooltipDuration));
        button.setTooltip(tooltip);
    }
}
