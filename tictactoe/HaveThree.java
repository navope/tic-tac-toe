package tictactoe;

class HaveThree{
    private boolean x;
    private boolean o;
    public HaveThree(){
        x = false;
        o = false;
    }
    public boolean getInfoX(){
        return x;
    }
    public boolean getInfoO(){
        return o;
    }
    public void setX( boolean newX){
        x = newX;
    }
    public void setO( boolean newO){
        o = newO;
    }
}