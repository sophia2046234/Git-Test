import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;

import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.*;
import java.io.File;
import java.io.FileWriter;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
public class respository {




//commit 一次的事情
    public static void main(String[] args) throws Throwable {
        File root = new File("gitme");
        if(!root.exists())
            root.mkdir();
        File gitF = new File("gitme/.git");
        if(!gitF.exists()) {//如果已经初始化过,那肯定有.git文件夹
                        //初始化git库,相当于命令行的 git init
            Git.init().setDirectory(root).call();
        }
        Git git = Git.open(root); //打开git库

                //好吧,随便写一个文件进去先
        File newFile = new File("gitme/"+System.currentTimeMillis()+".java");
        FileWriter fw = new FileWriter(newFile);
        fw.write(System.currentTimeMillis() + " ABC");
        fw.flush();
        fw.close();

                //添加文件咯,相当于 git add .
        git.add().addFilepattern(".+").call();

                //然后当然是提交啦,相当于 git commit
        git.commit().setCommitter("wendal", "wendal1985@gmail.com").setMessage("Try jgit!").call();

        //接下来,我们看看log信息
        for(RevCommit revCommit : git.log().call()){
            System.out.println(revCommit);
            System.out.println(revCommit.getFullMessage());
            System.out.println(revCommit.getCommitterIdent().getName() + " " + revCommit.getCommitterIdent().getEmailAddress());
        }
    }


}