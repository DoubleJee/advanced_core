package design_pattern.adapter.plug;

public class ThreePlugAdapter implements TwoPlug {

    private ThreePlug threePlug;

    public ThreePlugAdapter(ThreePlug threePlug){
        this.threePlug = threePlug;
    }

    @Override
    public void twoPlugDisPlay() {
        threePlug.threePlugDisPlay();
    }
}
