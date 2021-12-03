import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { MirroredUser } from '../models/MirroredUser';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLogin: MirroredUser;

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  login() {
    this.userService.login(this.userLogin).subscribe((resp: MirroredUser)=> {
      this.userLogin = resp;

      environment.token = this.userLogin.token
      environment.idUser = this.userLogin.idUser;
      environment.userName = this.userLogin.userName;
      environment.userPhoto = this.userLogin.userPhoto;

      console.log(environment.token)
      console.log(environment.idUser)
      console.log(environment.userName)
      console.log(environment.userPhoto)
     

      this.router.navigate(['outset'])
    })
  }

}
