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
  {
  }
}


