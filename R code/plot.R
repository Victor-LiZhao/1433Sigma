#setwd("g:/working/analysis")
table<-read.csv("result-right.csv")
sigma<-table[c(1,8)]
type="zeta"
newsigma<-sigma[order(sigma[,2],decreasing=TRUE),]
residues<-"R"
residues[1500]<-"R"
dim(residues)<-c(500,3)
values<-1:500
dim(values)<-c(500,1)
for(i in 1:500)
{
  tempseq=as.character(newsigma[[i,1]])
  tempseq<-strsplit(tempseq,split=NULL)
  tempseq<-tempseq[[1]]
  residues[i,1]<-tempseq[1]
  residues[i,2]<-tempseq[2]
  residues[i,3]<-tempseq[3]
  values[i]<-newsigma[[i,2]]
}
data<-data.frame(AA=residues,V=values)
for(i in 1:3)
{
#  if(i==1) index=3;
#  if(i==2) index=2;
#  if(i==3) index=1;
  name<-paste(type,"+",i,".jpeg",sep="")
  plotname<-paste(type," P+",i," Position",sep="")
  jpeg(file=name)
  plot(data[1:50,i],main=plotname)
  dev.off()
}