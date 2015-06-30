setwd("d:/analysis")

# scatter the predicted value and true value
#get the source data
tablel<-read.csv("result-left.csv")
tabler<-read.csv("result-right.csv")
tablelt<-read.csv("result-left-true.csv")
tablert<-read.csv("result-right-true.csv")

fun.getData <- function(index,type="default")
{
  datalp<-tablel[c(1,index)]
  datalt<-tablelt[c(1,index)]
  datarp<-tabler[c(1,index)]
  datart<-tablert[c(1,index)]
  for(i in 1:dim(datarp)[1])
  {
    if(datalp[i,2]<0)
      datalp[i,2]<-0
    if(datarp[i,2]<0)
      datarp[i,2]<-0
  }
  resultl<-merge(datalp,datalt,by=c("seq"))
  resultr<-merge(datarp,datart,by=c("seq"))
  resultl<-as.data.frame(resultl)
  resultr<-as.data.frame(resultr)
  result<-rbind(resultl,resultr)
  return(result)
}



result<-fun.getData(2)
plot(result[,2:3])
