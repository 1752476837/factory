import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Tarry
 * @create 2019/10/16 11:17
 */
public class Bcirpt {
    public static void main(String[] args) {
        String password = "111111";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //使用BCrypt加密，每次加密使用一个随机盐
            String encode = bCryptPasswordEncoder.encode(password);
            System.out.println(encode);

//            校验密码
        boolean matches = bCryptPasswordEncoder.matches("111111", "$2a$10$.UYJDUVX73GZdQUrOqjaq.2.YqGTZ3Wy1j8JvgELtQ/wAphVjEp.u");
        System.out.println(matches);

    }
}
