package com.ngs.stash.externalhooks.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.bitbucket.auth.AuthenticationContext;
import com.atlassian.bitbucket.cluster.ClusterService;
import com.atlassian.bitbucket.hook.repository.RepositoryHookService;
import com.atlassian.bitbucket.hook.script.HookScriptService;
import com.atlassian.bitbucket.permission.Permission;
import com.atlassian.bitbucket.permission.PermissionService;
import com.atlassian.bitbucket.project.ProjectService;
import com.atlassian.bitbucket.repository.RepositoryService;
import com.atlassian.bitbucket.server.StorageService;
import com.atlassian.bitbucket.user.SecurityService;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.scheduler.SchedulerService;
import com.atlassian.upm.api.license.PluginLicenseManager;

@Path("/")
@Scanned
public class Settings {
  private UserManager userManager;

  private AuthenticationContext authenticationContext;
  private PermissionService permissionService;

  @Inject
  public Settings(
      @ComponentImport UserManager userManager,
      @ComponentImport RepositoryService repositoryService,
      @ComponentImport SchedulerService schedulerService,
      @ComponentImport HookScriptService hookScriptService,
      @ComponentImport RepositoryHookService repositoryHookService,
      @ComponentImport ProjectService projectService,
      @ComponentImport PluginSettingsFactory pluginSettingsFactory,
      @ComponentImport SecurityService securityService,
      @ComponentImport AuthenticationContext authenticationContext,
      @ComponentImport("permissions") PermissionService permissionService,
      @ComponentImport PluginLicenseManager pluginLicenseManager,
      @ComponentImport ClusterService clusterService,
      @ComponentImport StorageService storageService) {
    this.permissionService = permissionService;
    // this.pullRequestService = pullRequestService;
    // this.projectService = projectService;
    // this.avatarService = avatarService;
    // this.authContext = authContext;

    this.userManager = userManager;
  }

  private boolean isSystemAdmin() {
    return permissionService.hasGlobalPermission(Permission.SYS_ADMIN);
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Path("/global/settings")
  public Response get() {
    if (!isSystemAdmin()) {
      return Response.status(401).build();
    }

    return Response.ok().build();
  }

  @PUT
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Path("/global/settings")
  public Response update(
      @PathParam("project_slug") String projectSlug,
      @PathParam("repository_slug") String repositorySlug,
      @PathParam("label_id") int labelId,
      @FormParam("name") String name,
      @FormParam("color") String color) {
    if (!isSystemAdmin()) {
      return Response.status(401).build();
    }

    return Response.ok().build();
  }
}
