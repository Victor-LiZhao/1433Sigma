#setwd("g:/working/analysis")
#table<-read.csv("all-result-left.csv")
table<-read.csv("all-result-right.csv")
types<-c("sigma","beta","epsilon","eta","gamma","tau","zeta")
for(index in 1:7)
{
  type=types[index]
  sigma<-table[c(1,index+1)]
#  sigmar<-tabler[c(1,i+1)]
  newsigma<-sigma[order(sigma[,2],decreasing=TRUE),]
  residues<-"R"
  residues[24000]<-"R"
  dim(residues)<-c(8000,3)
  values<-1:8000
  dim(values)<-c(8000,1)
  for(i in 1:8000)
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
    name<-paste(type,"+",i,"-all.jpeg",sep="")
    plotname<-paste(type," P+",i," Position-all",sep="")
    jpeg(file=name)
    plot(data[1:500,i],main=plotname)
    dev.off()
  }
}