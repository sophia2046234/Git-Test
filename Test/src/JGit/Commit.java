package JGit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.filter.PathFilterGroup;

public class Commit {
	static Git git ;
/** 
     * 将文件列表提交到git仓库中 
     * @param gitRoot git仓库目录 
   	 * @throws Throwable 
     * @throws IOException  
     */  
    public static String commitToGitRepository() throws Throwable { //提交到 
    	InitRespo respo= new InitRespo();
    	respo.initRespo();
    	git = Git.open(respo.rootDir); 
        if (git.getRepository()!=null ) {  
        	//写一个文件进去
        	File newFile = new File("C:/workspace/eclipse/JGit/"+"git"+".java");
    	    FileWriter fw = new FileWriter(newFile);
    	    fw.write(System.currentTimeMillis() + " ABC");
    	    fw.flush();
    	    fw.close();
           //1、将文件添加进去
    	    AddCommand addCmd = git.add();  
    	    addCmd.addFilepattern(newFile.toString());
           // git.add().addFilepattern(".+").call();
            //2、提交
            CommitCommand commitCmd = git.commit();  
            commitCmd.setOnly(newFile.toString());
            RevCommit revCommit = commitCmd.setCommitter("zoe", "625058929@qq.com").setMessage("commit by zoe").call();  //进行标记
//                .setMessage("commit by zoe").call();  
            return revCommit.getName();  
        }  
        return null;  
    } 
	public static void main(String[] args) throws Throwable {
	InitRespo respo= new InitRespo();
	respo.initRespo();
    Commit com= new Commit();
    com.commitToGitRepository();
//    for(RevCommit revCommit :git.log().call()){
//   System.out.println(revCommit);
//   System.out.println(revCommit.getFullMessage());
//   System.out.println(revCommit.getCommitterIdent().getName() + " " + revCommit.getCommitterIdent().getEmailAddress());
//   }
	}


}
