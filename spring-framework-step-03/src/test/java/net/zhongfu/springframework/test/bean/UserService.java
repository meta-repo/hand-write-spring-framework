package net.zhongfu.springframework.test.bean;

/**
 * 模拟用户 Bean 对象
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 */
public class UserService {

    private String name;

    /**
     * 测试时，可以尝试注释掉这个构造函数
     */
    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("toString").append(name);
        return sb.toString();
    }

}
