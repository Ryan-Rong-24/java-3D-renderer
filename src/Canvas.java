import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Canvas {
    int width = 0, height = 0;
    Color canv [][];
    public Canvas(int width, int height){
        this.width = width;
        this.height = height;
        canv = new Color[height][width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                canv[i][j] = new Color();
            }
        }
    }
    public void printCanvas(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.canv[i][j].color.printTuple();
            }
            System.out.println();
        }
    }
    public void writePixel(int i, int j, Color col){
        this.canv[j][i] = col;
    }

    public Color getPixel(int i, int j){
        return this.canv[i][j];
    }

    public String writeToPPM() {
        String result = "P3" + "\n" + this.width + " " + this.height + "\n" + 255+"\n";
        for(int i = 0; i < this.width; i++){
            String temp="";
            for (int j = 0; j <this.height; j++){
                Color c = this.getPixel(i,j);
                String red = (int)(c.color.x*255) + " ";
                String green = (int)(c.color.y*255)+ " ";
                String blue = String.valueOf((int)(c.color.z * 255));
                if (j==this.height - 1){
                    blue += "\n";
                }
                else{
                    blue += " ";
                }
                if(temp.length()+red.length()<=70){
                    temp += red;
                }
                else{
                    result += temp + "\n";
                    temp = red;
                }
                if(temp.length()+green.length()<=70){
                    temp += green;
                }
                else{
                    result += temp + "\n";
                    temp = green;
                }
                if(temp.length()+blue.length()<=70){
                    temp += blue;
                }
                else{
                    result += temp + "\n";
                    temp = blue;
                }
            }
            result += temp;
        }
        return result;
    }

    public String writeToPPM2() {
        String header = "P3" + "\n" + this.width + " " + this.height + "\n" + 255 + "\n";
        String body = "";
        for (int i = 0; i < this.height; i++) {
            String temp = "";
            for (int j = 0; j < this.width; j++) {
                Color c = this.canv[i][j];
                String red = (int)(c.color.x*255) +" ";
                String green = (int)(c.color.y*255)+" ";
                String blue = (int)(c.color.z*255)+"";
                if(j==(this.height-1)){
                    blue+="\n";
                } else {
                    blue+=" ";
                }
                if(temp.length()+red.length()<=70){
                    temp+=red;
                } else {
                    body += temp + "\n";
                    temp = red;
                }
                if(temp.length()+green.length()<=70){
                    temp+=green;
                } else {
                    body += temp + "\n";
                    temp = green;
                }
                if(temp.length()+blue.length()<=70){
                    temp+=blue;
                } else {
                    body += temp + "\n";
                    temp = blue;
                }
            }
//            System.out.println(temp.length());
            body+=temp;
//            break;
        }
        return header+body;
    }
    public void printPPM(String name) throws IOException {
        String graph = this.writeToPPM2();
        String fileName = name + ".ppm";
        try{
            FileWriter out = new FileWriter(fileName);
            out.write(graph);
            out.close();
            System.out.println("Writing Successful");
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    // ppm format
    //P3  (前三行是ppm文件头)
    //5 3 (width height)
    //255 (用的scale)
    //(图片):
    //255 0 0 0 0 0 (每三个数字是一组)
    //1 2 3 etc...
}
