#setwd("g:/working/analysis")
tablel<-read.csv("result-left.csv")
tabler<-read.csv("result-right.csv")

types<-c("sigma","beta","epsilon","eta","gamma","tau","zeta")
for(i in 1:7)
{
  type=types[i]
  sigmal<-tablel[c(1,i+1)]
  sigmar<-tabler[c(1,i+1)]
  newsigmal<-sigmal[order(sigmal[,2],decreasing=TRUE),]
  newsigmar<-sigmar[order(sigmar[,2],decreasing=TRUE),]
  seqtop50<-"XXX"
  seqtop50[50]<-"XXX"
  for(j in 1:50)
  {
    seqtop50[j]=paste(as.character(newsigmal[j,1]),as.character(newsigmar[j,1]),sep="")
  }
  seqtop50=as.data.frame(seqtop50)
  outname<-paste("d:/top50/",type,".txt",sep="")
  write.table(seqtop50, file = outname, row.names = F, quote = F)
}


