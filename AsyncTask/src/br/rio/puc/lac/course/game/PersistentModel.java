package br.rio.puc.lac.course.game;

import android.os.Handler;
import android.os.Looper;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:bmeike@callmeike.net">Blake Meike</a>
 */
public class PersistentModel implements Runnable {
    private final Handler ui;

    /**
     * @param ui
     */
    public PersistentModel(Handler ui) { this.ui = ui; }

    /**  @see java.lang.Runnable#run() */
    @Override
    public void run() {
        Looper.prepare();
        Looper.loop();
    }

    public Handler getHandler() {
        // TODO Auto-generated method stub
        return null;
    }
}
