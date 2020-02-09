package io.quickcoding.pruebaarkus.request;

public class ListenerRequest {


    private Listener listener;

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }


    public interface Listener
    {
        public void onObjectError(String message);
        public void onDataLoaded(String message,Object object);

    }

    public void onDataLoaded(String message,Object object)
    {
        if (listener !=null)
        {
            listener.onDataLoaded(message, object);
        }
    }

    public   void onObjectError(String message)
    {
        if (listener !=null)
        {
            listener.onObjectError(message);
        }
    }


}
