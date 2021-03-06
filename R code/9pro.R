setwd("d:data/new-9pro")
library("glmnet")
result<-1:1000
result<-matrix(result,nrow=500,ncol=2)
for(i in 0:499)
{
  print(i)
  name<-paste("single-",i,".txt",sep="")
  allM<-read.table(name)
  rowNum<-dim(allM)[1]
  x<-allM[2:rowNum,1:27]
  y<-allM[2:rowNum,28]
  newx<-allM[1,1:27]
  y<-as.matrix(y)
  x<-as.matrix(x)
  newx<-as.matrix(newx)
  cv1=cv.glmnet(x,y)
  result[i+1,1]<-allM[1,28]
  result[i+1,2]<-predict(cv1,newx,s="lambda.min")
}
errorsum<-0;
plussum<-0;
average<-mean(result[,1])
for(i in 1:500)
{
  temperror<-(result[i,1]-result[i,2])*(result[i,1]-result[i,2])
  errorsum<-errorsum+temperror
  tempsum=(result[i,1]-average)*(result[i,1]-average)
  plussum=plussum+tempsum
}
PCC<-sqrt(1-(errorsum/plussum))
print(PCC)
RMSE<-sqrt(errorsum/500)
print(RMSE)
write.table(result, file = "d:/result/9pro.txt", row.names = F, quote = F)