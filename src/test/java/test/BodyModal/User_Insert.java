package test.BodyModal;

public class User_Insert {

    private String userId;
    private String requestTs;
    private String lang;
    private Data data;

    public User_Insert(String userId, String requestTs, String lang, String userIdData, String userNm, String deptCd, String authCd, int telNo, String email, String regUser, String staffCd) {
        this.userId = userId;
        this.requestTs = requestTs;
        this.lang = lang;
        this.data = new Data(userIdData, userNm, deptCd, authCd, telNo, email, regUser, staffCd);
    }

    public class Data {
        private String userId;
        private String userNm;
        private String deptCd;
        private String authCd;
        private int telNo;
        private String email;
        private String regUser;
        private String staffCd;

        public Data(String userIdData, String userNm, String deptCd, String authCd, int telNo, String email, String regUser, String staffCd) {
            this.userId = userIdData;
            this.userNm = userNm;
            this.deptCd = deptCd;
            this.authCd = authCd;
            this.telNo = telNo;
            this.email = email;
            this.regUser = regUser;
            this.staffCd = staffCd;
        }
    }
}
