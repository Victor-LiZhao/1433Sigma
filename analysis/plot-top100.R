setwd("d:/analysis")
fun.getTop100 <- function(index, type="default"){
  #get the source data
  tablel<-read.csv("result-left.csv")
  tabler<-read.csv("result-right.csv")

  #combine N-terminus and C-terminus
  sigmal<-tablel[c(1,index)]
  sigmar<-tabler[c(1,index)]
  sigma<-"R"
  sigma[3000]<-"R"
  dim(sigma)<-c(1000,3)
  for(i in 1:500)
  {
    sigma[i,1]<-as.character(sigmal[i,1])
    sigma[i,2]<-as.numeric(sigmal[i,2])
    sigma[i,3]<-1; #1 represent  left
  }
  for(i in 1:500)
  {
    sigma[i+500,1]<-as.character(sigmar[i,1])
    sigma[i+500,2]<-sigmar[i,2]
    sigma[i+500,3]<-2; #2 represent right
  }
  newsigma<-sigma[order(as.numeric(sigma[,2]),decreasing=TRUE),]
  newsigma_top100<-newsigma[1:100,]
  return(newsigma_top100)           ## 返回值
}

index<-2
type="sigma"
result<-fun.getTop100(2)
for(i in 2:8)
{
  tempresult<-fun.getTop100(i)
  result<-merge(result,tempresult,by=c("V1","V3"))
}
write.table(result, file = "d:/top100-result.txt", row.names = F, quote = F)

#plot the top100
lnum<-0
for(i in 1:dim(result)[1])
{
  if(result[i,2]==1)
    lnum=lnum+1
}
rnum<-dim(result)[1]-lnum
residues<-"R"
residues[rnum*3]<-"R"
dim(residues)<-c(rnum,3)
lindex<-1;
for(i in 1:dim(result)[1])
{
  if(result[i,2]==2)
  {
    tempseq=as.character(result[[i,1]])
    tempseq<-strsplit(tempseq,split=NULL)
    tempseq<-tempseq[[1]]
    residues[lindex,1]<-tempseq[1]
    residues[lindex,2]<-tempseq[2]
    residues[lindex,3]<-tempseq[3]
    lindex=lindex+1
  }
}
data<-data.frame(AA=residues)
for(i in 1:3)
{
  #  if(i==1) index=3;
  #  if(i==2) index=2;
  #  if(i==3) index=1;
  name<-paste("top100+",i,".jpeg",sep="")
  plotname<-paste("top100+"," P+",i," Position",sep="")
  jpeg(file=name)
  plot(data[,i],main=plotname)
  dev.off()
}