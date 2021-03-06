#setwd("d:data/6pro-ac-nosampling")
#library("glmnet")
result<-1:1000
result<-matrix(result,nrow=500,ncol=2)
outname<-"d:/result/zeta-right-9pro-ac.txt"
dems<-54
for(i in 0:499)
{
  print(i)
  name<-paste("single-",i,".txt",sep="")
  allM<-read.table(name)
  rowNum<-dim(allM)[1]
  x<-allM[2:rowNum,1:dems]
  y<-allM[2:rowNum,dems+1]
  newx<-allM[1,1:dems]
  y<-as.matrix(y)
  x<-as.matrix(x)
  newx<-as.matrix(newx)
  alpha_v<-seq(0,1,0.1)
  fit = lapply(alpha_v,function(a){cv.glmnet(x,y,alpha=a)})
  s = which.min(sapply(fit,function(x)min(x$cvm)))
  result[i+1,1]<-allM[1,dems+1]
  result[i+1,2]<-predict(fit[[s]],newx,s=fit[[s]]$lambda.min)
#  cv1=cv.glmnet(x,y,parallel=TRUE)
#  result[i+1,1]<-allM[1,55]
#  result[i+1,2]<-predict(cv1,newx,s="lambda.min")
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
write.table(result, file = outname, row.names = F, quote = F)