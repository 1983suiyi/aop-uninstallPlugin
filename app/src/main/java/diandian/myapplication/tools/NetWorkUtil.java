package diandian.myapplication.tools;

import java.io.IOException;

/**
 * Created by funplus on 2018/6/20.
 */

public class NetWorkUtil {

    /**
     *
     * @param target_name
     *            IP地址或域名
     * @return
     * @throws IOException
     */
    public static boolean ping(String target_name) {

        Runtime runtime = Runtime.getRuntime();

        String ping_command = "ping -c 1 " + target_name;

        System.out.println("命令格式：" + ping_command);
        try {
            java.lang.Process process = runtime.exec(ping_command);
            if (null == process)
                return false;
            int result = process.waitFor();
            if(result == 0){
                return true;
            }else {
                return false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (IOException e){

        }
        return false;
    }


}
