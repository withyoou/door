>   Hotfix： 
1.	将代码更改提交到develop分支并push到远程仓库
2.	切换到master分支pull最新代码
3.	执行mvn jgitflow:hotfix-start -DautoVersionSubmodules=true -DreleaseVersion=xxx(xxx为版本号)
4.	执行 git cherry-pick xxx(xxx为刚刚commit的ID)
5.	执行mvn jgitflow:hotfix-finish -DnoHotfixBuild=true -DconsistentProjectVersions=true
6.	执行git push将修改push到远程develop分支
7.	再次切换到master分支，git push/ git push –tags 将修改和版本号提交到远程master分支。Hotfix结束。

>	Release：
1.	将develop分支和master分支代码更新到最新
2.	切到master分支
3.	执行mvn jgitflow:release-start -DautoVersionSubmodules=true -DreleaseVersion=<version>
4.	执行mvn jgitflow:release-finish -DnoReleaseBuild=true