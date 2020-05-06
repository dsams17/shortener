import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-retriever',
  templateUrl: './retriever.component.html',
  styleUrls: ['./retriever.component.css']
})
export class RetrieverComponent implements OnInit {

  error: string;

  constructor(private route: ActivatedRoute,
    private http: HttpClient) { 
    }

  ngOnInit() {
    this.retrieveUrl();
  }

  retrieveUrl(){
    const url = this.route.snapshot.paramMap.get('url');

    this.http.get("http://localhost:3000/" + url)
    .subscribe((data: any) => {
      console.log(data);
      if (!data){
        this.error = "Shortened URL entered not found in database. Please try again.";
        return;
      }

      console.log(data['original']);
      window.location.href = data['original'];
    },
    error => this.error = "Shortened URL entered not found in database. Please try again.")
  }
}
