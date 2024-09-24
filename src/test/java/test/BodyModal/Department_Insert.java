package test.BodyModal;

public class Department_Insert {

    private String userId;
    private String requestTs;
    private String lang;
    private Data data;

    public Department_Insert(String userId, String requestTs, String lang, String deptCd, String deptNm) {
        this.userId = userId;
        this.requestTs = requestTs;
        this.lang = lang;
        this.data = new Data(deptCd, deptNm);
    }

    public class Data {
        private String deptCd;
        private String deptNm;

        public Data(String deptCd, String deptNm) {
            this.deptCd = deptCd;
            this.deptNm = deptNm;
        }
    }
}
