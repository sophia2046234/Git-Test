package JGit;

import java.io.IOException;
import java.net.URISyntaxException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;

public class Remote {

	/**
	 * @param args
	 */
	 // 执行 git remote add 命令
	 // 实例化一个RemoteConfig 对象，用户配置远端仓库
	static Git git ;
	static RemoteConfig remoteConfig;
	public void remote() throws Throwable{
	InitRespo respo= new InitRespo();
	respo.initRespo();
	git = Git.open(respo.rootDir); 
	StoredConfig config = git.getRepository().getConfig();
   
	 try {
	 remoteConfig = new RemoteConfig(config, "origin");
	 // 设置你的远端地址
	 URIish uri = new URIish("ssh://git@github.com/sophia2046234/Git-Test.git");
		
	 // 设置哪个分支
	 RefSpec refSpec = new RefSpec("+refs/head/*:refs/remotes/origin/*");
	 // 更新配置
	 remoteConfig.addFetchRefSpec(refSpec);
	 remoteConfig.addPushRefSpec(refSpec);
	 remoteConfig.addURI(uri);
	 remoteConfig.addPushURI(uri);
	 // 更新配置
	 remoteConfig.update(config);
	 // 保存到本地文件中
	 config.save();
	 
	 System.out.println("git remote add success.");
	 } catch (URISyntaxException e) {

	e.printStackTrace();
	 } catch (IOException e) {
	 e.printStackTrace();
	 }

	}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
//   Remote rem = new Remote();
//   rem.remote();
//// System.out.println(git.push().getRefSpecs());
//   
//   git.push().setRemote("git://github.com/sophia2046234/Git-Test");
//   git.push();
//   }
	

	    File gitDir = new File("c:\\new-repo\\");

	try {

	      String localPath = "c:\\new-repo\\";
	      Repository localRepo = new FileRepository(localPath + ".git");
	      localRepo.create();
	      Git git = new Git(localRepo);

	      git.add().addFilepattern("c:\\test.txt").call();

	      git.commit().setMessage("testcommit").call();

	      git.push().call();

	  localRepo.close();
	} catch (IllegalStateException ise) {
	        System.out.println("The repository already exists!");
	} catch (IOException ioe) {
	        System.out.println("Failed to create the repository!");
	} catch (NoFilepatternException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (GitAPIException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	  
}
}
