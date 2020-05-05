
package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.servlet.http.Part;


@Named(value = "uploadBean")
@SessionScoped
public class UploadBean implements Serializable {

    private Part image;
    
    public void doUpload(){
        try {
            InputStream in = image.getInputStream();
            
            File f = new File("D:\\PRJ4\\gitCode\\graffiti\\graffiti\\src\\main\\webapp\\assets\\images\\" + image.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            
            byte[] buffer = new byte[1024];
            int length;
            
            while((length= in.read(buffer))>0){
                out.write(buffer, 0, length);
            }
            
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
    
}
