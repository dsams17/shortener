import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-generator',
  templateUrl: './generator.component.html',
  styleUrls: ['./generator.component.css']
})
export class GeneratorComponent {
  constructor(private http: HttpClient) { }

  transformedUrl: string;
  url: string;
  update(url: string) { this.url = url; }

  sendUrl6(){
    if (!this.url){
      this.transformedUrl = "No URL entered.";
      return;
    }

    this.url = this.url.replace(/\//g, '%2F')
    this.http.get("http://localhost:3000/shorten6/" + this.url)
    .subscribe((data: any) => {
      console.log(data);
      this.transformedUrl = "Your shortened URL is: http://localhost:4200/" + data['generated'];
    },
    error => this.transformedUrl = "Invalid URL entered.")
  };

  sendUrl8(){
    if (!this.url){
      this.transformedUrl = "No URL entered.";
      return;
    }

    this.url = this.url.replace(/\//g, '%2F')
    this.http.get("http://localhost:3000/shorten8/" + this.url)
    .subscribe((data: any) => {
      console.log(data);
      this.transformedUrl = "Your shortened URL is: http://localhost:4200/" + data['generated'];
    },
    error => this.transformedUrl = "Invalid URL entered.")
  };
  
  title = 'frontend';
}
