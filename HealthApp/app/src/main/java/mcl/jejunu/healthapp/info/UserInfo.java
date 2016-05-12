package mcl.jejunu.healthapp.info;

/**
 * Created by neo-202 on 2016-05-11.
 */
public  class UserInfo {

    private static UserInfo userInfo;
    public static UserInfo getInstance(){
        if(userInfo == null){
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    private String nickname;
    private int height;
    private int weight;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
