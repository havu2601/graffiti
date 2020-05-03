/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author havu2601
 */
@Entity
@Table(name = "video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
    @NamedQuery(name = "Video.findByVideoId", query = "SELECT v FROM Video v WHERE v.videoId = :videoId"),
    @NamedQuery(name = "Video.findByVideoPath", query = "SELECT v FROM Video v WHERE v.videoPath = :videoPath"),
    @NamedQuery(name = "Video.findByVideoTitle", query = "SELECT v FROM Video v WHERE v.videoTitle = :videoTitle")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "video_id")
    private Integer videoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "video_path")
    private String videoPath;
    @Size(max = 200)
    @Column(name = "video_title")
    private String videoTitle;

    public Video() {
    }

    public Video(Integer videoId) {
        this.videoId = videoId;
    }

    public Video(Integer videoId, String videoPath) {
        this.videoId = videoId;
        this.videoPath = videoPath;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (videoId != null ? videoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.videoId == null && other.videoId != null) || (this.videoId != null && !this.videoId.equals(other.videoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Video[ videoId=" + videoId + " ]";
    }
    
}
