setwd("d:/analysis")
fun.getTop500 <- function(index, type="default"){
  #get the source data
  tablel<-read.csv("all-result-left.csv")
  tabler<-read.csv("all-result-right.csv")
  
  #combine N-terminus and C-terminus
  sigmal<-tablel[c(1,index)]
  sigmar<-tabler[c(1,index)]
  sigma<-"R"
  sigma[48000]<-"R"
  dim(sigma)<-c(16000,3)
  for(i in 1:8000)
  {
    sigma[i,1]<-as.character(sigmal[i,1])
    sigma[i,2]<-as.numeric(sigmal[i,2])
    sigma[i,3]<-1; #1 represent  left
  }
  for(i in 1:8000)
  {
    sigma[i+8000,1]<-as.character(sigmar[i,1])
    sigma[i+8000,2]<-sigmar[i,2]
    sigma[i+8000,3]<-2; #2 represent right
  }
  newsigma<-sigma[order(as.numeric(sigma[,2]),decreasing=TRUE),]
  newsigma_top500<-newsigma[1:500,]
  newsigma_top500<-as.data.frame(newsigma_top500)
  return(newsigma_top500)     
}



index<-2
type="sigma"
result<-fun.getTop500(2)

#get sigma special
for(i in 3:8)
{
  
  tempresult<-fun.getTop500(i)
  
  #get the left sequence subset
  newresult<-"NULL"
  selectResult_Left1<-subset(result,V3=="1")
  selectResult_Left2<-subset(tempresult,V3=="1")
  diffseq<-setdiff(selectResult_Left1[,1],selectResult_Left2[,1])
  for(j in 1:length(diffseq))
  {
    temp<-subset(result,V3=="1" & V1==diffseq[j])
    temp<-as.data.frame(temp)
    if(newresult=="NULL")
      newresult<-temp
    else
      newresult<-rbind(newresult,temp[1,])
  }
  
  #get the left sequence subset
  newresult2<-"NULL"
  selectResult_right1<-subset(result,V3=="2")
  selectResult_right2<-subset(tempresult,V3=="2")
  diffseq2<-setdiff(selectResult_right1[,1],selectResult_right2[,1])
  for(j in 1:length(diffseq2))
  {
    temp<-subset(result,V3=="2" & V1==diffseq2[j])
    temp<-as.data.frame(temp)
    if(newresult2=="NULL")
      newresult2<-temp
    else
      newresult2<-rbind(newresult2,temp[1,])
  }
  
  result<-rbind(newresult,newresult2)
}
write.table(result, file = "d:/sigmaSpecify-result-all.txt", row.names = F, quote = F)