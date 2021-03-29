public class Material extends General{
    double ambient = 0.1; //1 means background color lighting is 亮，背景发光
    double diffused = 0.9;
    double specular = 0.9;
    double shininess = 200;
    Color c = new Color(1,1,1);
    public Material(){}
    public Color lighting(Light l,Computation comp, Boolean isInShadow){

        Color effectiveColor = l.intensity.multiply(this.c);
        Tuple lightV = l.pos.subtract(comp.point).normalize();
        Color ambient = effectiveColor.multiplyScalar(this.ambient);
        Color diffuse,specular;
        Matrix transition = comp.shape.transform;
        double lDN = lightV.dot(comp.normal); //if LDN (light dot normal) < 0 you can't see it bc light no hit it

        if(isInShadow) return ambient;
        if(lDN<0){
            diffuse = specular = black;
        } else {
            diffuse = effectiveColor.multiplyScalar(this.diffused).multiplyScalar(lDN);
            Tuple reflectV = lightV.multiplyScalar(-1).reflect(comp.normal);
            double rDE = reflectV.dot(comp.eye);
            if(rDE<0) { //eye cannot see
                specular = black;
            } else {
                double factor = Math.pow(rDE,this.shininess);
                specular = l.intensity.multiplyScalar(this.specular*factor);
            }
        }
        return ambient.add(diffuse.add(specular));
    }
}
