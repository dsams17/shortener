import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GeneratorComponent } from './generator/generator.component';
import { RetrieverComponent } from './retriever/retriever.component';


const routes: Routes = [
  { path: ':url', component: RetrieverComponent},
  { path: '**', component: GeneratorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
