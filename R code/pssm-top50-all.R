#setwd("g:/working/analysis")
tablel<-read.csv("all-result-left.csv")
tabler<-read.csv("all-result-right.csv")

types<-c("sigma","beta","epsilon","eta","gamma","tau","zeta")
for(i in 1:7)
{
  type=types[i]
  sigmal<-tablel[c(1,i+1)]
  sigmar<-tabler[c(1,i+1)]
  newsigmal<-sigmal[order(sigmal[,2],decreasing=TRUE),]
  newsigmar<-sigmar[order(sigmar[,2],decreasing=TRUE),]
  seqtop500<-"XXX"
  seqtop500[500]<-"XXX"
  for(j in 1:500)
  {
    seqtop500[j]=paste(as.character(newsigmal[j,1]),as.character(newsigmar[j,1]),sep="")
  }
  seqtop500=as.data.frame(seqtop500)
  outname<-paste("d:/top50/",type,"-all.txt",sep="")
  write.table(seqtop500, file = outname, row.names = F, quote = F)
}

