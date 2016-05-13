package cn.dream.bean.borrow;
/**
 * 借阅状态
 *
 */
public enum State {
    
    /** 正常借阅 */
    NORMAL {public String getName(){return "正常";}},
    /** 归还 */
    BACK{public String getName(){return "归还";}},
    /** 续借 */
    RENEW {public String getName(){return "续借";}},
    /** 逾期 */
    LATE {public String getName(){return "逾期";}};
     public abstract String getName();
}
