# maven-archetype

##启动命令
mvn archetype:generate -DarchetypeGroupId=com.roy -DarchetypeArtifactId=maven-archetype -DarchetypeVersion=1.0.0-SNAPSHOT  -DgroupId=com.roy.gsed -DartifactId=gsed-server -Dpackage=com.roy.gsed -Dproduct=gsed -Dversion=1.0.0-SNAPSHOT -DarchetypeCatalog=local

##命令说明: 
- DarchetypeGroupId=com.roy 脚手架的groupId
- DarchetypeArtifactId=maven-archetype
- DarchetypeVersion=1.0.0-SNAPSHOT  脚手架版本
- DgroupId=com.roy.gsed 需要生成的项目groupId （gsed是产品名）
- DartifactId=gsed-server 需要生成的项目artifactId (后台gsed-server，如kprm-server)
- Dversion=1.0.0-SNAPSHOT 需要生成的版本号
- Dproduct=gsed 产品代号
- DarchetypeCatalog=internal 使用私有仓库脚手架jar包, 前提：已经把脚手架发布到私有仓库中
- DarchetypeCatalog=local 使用本地脚手架jar包, 如果不配置, 它会到中央仓库去下载, 会导致失败
-X debug模式
