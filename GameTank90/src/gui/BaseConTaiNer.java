package gui;

import javax.swing.*;

/**
 * Created by IT MAN on 5/26/2016.
 */
public abstract class BaseConTaiNer extends JPanel {
    public BaseConTaiNer(){
        initPanel();
        initComps();
        addComps();
    }

    protected abstract void addComps();

    protected abstract void initComps();

    protected abstract void initPanel();
}
