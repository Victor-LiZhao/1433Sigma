setwd("d:/analysis")

# scatter the sigma predicted value and other istype predicted value
#get the source data
tablel<-read.csv("result-left.csv")
tabler<-read.csv("result-right.csv")

fun.getData <- function(index,type="default")
{
  datalp<-tablel[c(1,2,index)]
  datarp<-tabler[c(1,2,index)]
  for(i in 1:dim(datarp)[1])
  {
    if(datalp[i,2]<0)
      datalp[i,2]<-0
    if(datarp[i,2]<0)
      datarp[i,2]<-0
    if(datalp[i,3]<0)
      datalp[i,3]<-0
    if(datarp[i,3]<0)
      datarp[i,3]<-0
  }
  resultl<-as.data.frame(datalp)
  resultr<-as.data.frame(datarp)
  result<-rbind(resultl,resultr)
  return(result)
}

for(i in 3:8)
{
  result<-fun.getData(i)
  name<-paste("scatter-sigma-",i,".jpeg",sep="")
  plotname<-paste("scatter-sigma-",i,".jpeg",sep="")
  jpeg(file=name)
  plot(result[,2:3],main=plotname)
  dev.off()
}
